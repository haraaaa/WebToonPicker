package com.pluu.webtoon.ui.settting

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceManager
import android.text.TextUtils
import android.view.MenuItem
import com.pluu.webtoon.R
import com.pluu.webtoon.common.PrefConfig
import com.pluu.webtoon.ui.login.LoginActivity

class SettingsActivity : AppCompatActivity() {


    override fun onRestart() {
        super.onRestart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, GeneralPreferenceFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class GeneralPreferenceFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.pref_settings)
            setHasOptionsMenu(true)
            bindPreferenceSummaryToValue(findPreference(PrefConfig.KEY_DEFAULT_WEBTOON))
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            if (item?.itemId == android.R.id.home) {
                activity?.finish()
                return true
            }
            return super.onOptionsItemSelected(item)
        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
           /* if (TextUtils.equals(LoginActivity::class.java.canonicalName, preference?.fragment)) {
                startActivity(Intent(activity, LicenseActivity::class.java))
                return true
            }*/

            Handler().postDelayed({
                changeTitle()
            }, 1000)

            if (TextUtils.equals(LoginActivity::class.java.canonicalName, preference?.fragment)) {
                startActivity(Intent(activity, LoginActivity::class.java))
                return true
            }
           return super.onPreferenceTreeClick(preference)
        }

        private val changeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            when (preference) {
                is ListPreference -> {
                    // For list preferences, look up the correct display value in
                    // the preference's 'entries' list.
                    val index = preference.findIndexOfValue(newValue.toString())

                    // Set the summary to reflect the new value.
                    preference.summary = if (index >= 0) preference.entries[index] else null
                }
                else -> preference.summary = newValue.toString()
            }
            true
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = changeListener
            changeListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.context)
                            .getString(preference.key, ""))

        }

        public fun changeTitle() {

            var login_title = findPreference("login_title")
            var tag_select = findPreference("tag_select")
            if(login_title.title.equals("로그인")) {
                login_title.setTitle("로그아웃")
                tag_select.isEnabled = true
            }/*else{
                login_title.setTitle("로그인")
                tag_select.isEnabled = false
            }*/
        }
    }
}
package com.pluu.webtoon.ui.detail

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.pluu.webtoon.R
import com.pluu.webtoon.adapter.DetailMultiAdapter
import com.pluu.webtoon.item.DetailView
import kotlinx.android.synthetic.main.fragment_daum_multi.*

/**
 * Daum Multi Fragment
 * Created by pluu on 2017-05-06.
 */
@SuppressLint("ValidFragment")
class DaumMultiFragment(private val gd: GestureDetector) : BaseDetailFragment() {

    private var adapter: DetailMultiAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_daum_multi, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initChattingSetting()
        firstBind()
    }

    private fun initChattingSetting() {
        val context = context ?: return
        chattingList?.layoutManager = LinearLayoutManager(context)

        adapter = object : DetailMultiAdapter(context) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMultiAdapter.ViewHolder {
                val holder = super.onCreateViewHolder(parent, viewType)
                holder.itemView.setOnClickListener { _ -> listener?.childCallToggle(false) }
                return holder
            }
        }
        chattingList?.adapter = adapter

        val padding = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
        chattingList?.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return gd.onTouchEvent(e)
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
        chattingList?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = padding
            }
        })
    }

    override fun loadView(list: List<DetailView>) {
        chattingList?.visibility = View.VISIBLE
        adapter?.clear()
        adapter?.setList(list)
        adapter?.notifyDataSetChanged()
        chattingList?.scrollToPosition(0)
        listener?.childCallToggle(true)
    }

}

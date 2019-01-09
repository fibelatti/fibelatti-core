package com.fibelatti.core.platform.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.fibelatti.core.extension.inflate

abstract class BaseAdapter<T>(
    private val hasFilter: Boolean = false
) : RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {

    private val items: MutableList<T> by lazy { mutableListOf<T>() }
    private val filteredItems: MutableList<T> by lazy { mutableListOf<T>() }

    private val collection: MutableList<T> get() = if (hasFilter) filteredItems else items

    override fun getItemCount(): Int = collection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    fun addAll(listItems: List<T>) {
        items.clear()
        items.addAll(listItems)

        if (hasFilter) {
            filteredItems.clear()
            filteredItems.addAll(listItems)
        }

        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredItems.clear()
        if (query.isBlank()) {
            filteredItems.addAll(items)
        } else {
            filteredItems.addAll(items.filter { filterCriteria(query, it) })
            if (filteredItems.isEmpty()) onEmptyFilterResult()
        }

        notifyDataSetChanged()
    }

    open fun filterCriteria(query: String, item: T): Boolean = false

    open fun onEmptyFilterResult() {}

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun View.bindView(item: T, viewHolder: ViewHolder)

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(getLayoutRes())
    ) {
        fun bind(item: T) = itemView.bindView(item, this)
    }
}

abstract class BaseAdapterWithDelegates(
    private val hasFilter: Boolean = false
) : RecyclerView.Adapter<BaseAdapterWithDelegates.ViewHolder>() {

    protected val delegateAdapters by lazy { SparseArrayCompat<BaseDelegateAdapter>() }

    protected val items: MutableList<BaseViewType> by lazy { mutableListOf<BaseViewType>() }
    protected val filteredItems: MutableList<BaseViewType> by lazy { mutableListOf<BaseViewType>() }

    private val collection: MutableList<BaseViewType> get() = if (hasFilter) filteredItems else items

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: BaseAdapterWithDelegates.ViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapterWithDelegates.ViewHolder =
        delegateAdapters[viewType]?.getLayoutRes()?.let { ViewHolder(parent, it) }
            ?: throw RuntimeException("No adapter mapped to viewType: $viewType")

    override fun getItemViewType(position: Int): Int = collection[position].getViewType()

    fun addAll(listItems: List<BaseViewType>) {
        items.clear()
        items.addAll(listItems)

        if (hasFilter) {
            filteredItems.clear()
            filteredItems.addAll(listItems)
        }

        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        filteredItems.clear()
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredItems.clear()
        if (query.isBlank()) {
            filteredItems.addAll(items)
        } else {
            filteredItems.addAll(items.filter { filterCriteria(query, it) })
            if (filteredItems.isEmpty()) onEmptyFilterResult()
        }

        notifyDataSetChanged()
    }

    open fun filterCriteria(query: String, item: BaseViewType): Boolean = false

    open fun onEmptyFilterResult() {}

    inner class ViewHolder(
        parent: ViewGroup,
        @LayoutRes layoutRes: Int
    ) : RecyclerView.ViewHolder(parent.inflate(layoutRes)) {

        fun bind(item: BaseViewType) = delegateAdapters[item.getViewType()]?.bindView()?.let {
            itemView.it(item, this)
        }
    }
}

interface BaseDelegateAdapter {
    @LayoutRes
    fun getLayoutRes(): Int

    fun bindView(): View.(item: BaseViewType, viewHolder: RecyclerView.ViewHolder) -> Unit
}

interface BaseViewType {
    fun getViewType(): Int
}

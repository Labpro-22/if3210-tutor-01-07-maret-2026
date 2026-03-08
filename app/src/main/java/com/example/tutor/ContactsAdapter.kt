package com.example.tutor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView.Adapter bridges a List<Contact> and the RecyclerView widget.
 *
 * How RecyclerView works:
 *   • It only creates enough ViewHolders to fill the screen (not one per item).
 *   • As the user scrolls, it *recycles* off-screen ViewHolders and calls
 *     onBindViewHolder() to fill them with new data.
 *   • This is far more efficient than ListView for large lists.
 *
 * Three methods you MUST override:
 *   getItemCount()      — how many items total?
 *   onCreateViewHolder()— inflate the item layout; called rarely (only for new slots)
 *   onBindViewHolder()  — bind data to views; called every time an item scrolls in
 */
class ContactsAdapter(
    private var contacts: List<Contact>
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    /**
     * ViewHolder caches references to the item's views so we don't call
     * findViewById() on every bind (that would be slow).
     */
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView  = itemView.findViewById(R.id.tvContactName)
        val tvPhone: TextView = itemView.findViewById(R.id.tvContactPhone)
    }

    // Called when RecyclerView needs a new ViewHolder (screen isn't full yet)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        // Inflate our item layout (item_contact.xml) and wrap it in a ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    // Called each time an item scrolls into view — bind the data at 'position'
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.tvName.text  = contact.name
        holder.tvPhone.text = contact.phone
    }

    override fun getItemCount(): Int = contacts.size

    /** Replace the list and tell RecyclerView everything changed */
    fun updateContacts(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()   // For production use DiffUtil for better performance
    }
}

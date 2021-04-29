package br.com.orlandoneto.clonegmail

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.orlandoneto.clonegmail.model.Email
import kotlinx.android.synthetic.main.email_item.view.*

class EmailAdapter(val emails: MutableList<Email>) :
    RecyclerView.Adapter<EmailAdapter.EmailviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return EmailviewHolder(view)
    }

    override fun getItemCount(): Int = emails.size

    override fun onBindViewHolder(holder: EmailviewHolder, position: Int) {
        holder.bind(emails[position])
    }


    inner class EmailviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(email: Email) {
            with(email) {
                val hash = user.hashCode()
                itemView.txt_icon.text = user.first().toString()
                itemView.txt_icon.background = itemView.oval(Color.rgb(hash, hash / 2, 0))
                itemView.text_user.text = user
                itemView.text_subject.text = subject
                itemView.text_preview.text = preview
                itemView.text_date.text = date

                itemView.text_user.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)
                itemView.text_subject.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)
                itemView.text_date.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)

                itemView.img_star.setImageResource(
                    if (stared) R.drawable.ic_baseline_star_24
                    else R.drawable.ic_baseline_star_border_24
                )
            }
        }
    }
}

fun View.oval(@ColorInt color: Int): ShapeDrawable {

    val oval = ShapeDrawable(OvalShape())
    with(oval) {
        intrinsicHeight = height
        intrinsicWidth = width
        paint.color = color
    }
    return oval
}

package com.juaracoding.ujian1batch4androidkotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param2: String? = null
    lateinit var article : Article

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param2 = it.getString(ARG_PARAM2)
//            @Suppress("DEPRECATION")
            article = it.getParcelable(ARG_PARAM1)!!
            Log.d("Detail fragment article", "Article received: $article")
//            article = it.getParcelable(ARG_PARAM1) ?: throw IllegalStateException("Article data not found in arguments")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shareButton = view.findViewById<Button>(R.id.btnShare)
        shareButton.setOnClickListener {
            if (article != null) {
                shareArticle(article)
            }
        }

        if(article != null) {
            view.findViewById<ImageView>(R.id.articleDetailImage)
                .setImageResource(article.imageResourceId)
            view.findViewById<TextView>(R.id.articleDetailTitle).text = article.title
            view.findViewById<TextView>(R.id.articleDetailDescription).text = article.description
            view.findViewById<TextView>(R.id.articleDetailOverview).text = article.overview
        }


    }

    private fun shareArticle(article: Article) {

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${article.title}\n${article.description}")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share using"))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Article, param2: String) =
            ListDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

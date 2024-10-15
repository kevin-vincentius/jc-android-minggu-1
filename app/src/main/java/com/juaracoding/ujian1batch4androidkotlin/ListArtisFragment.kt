package com.juaracoding.ujian1batch4androidkotlin

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Article"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListArtisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListArtisFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_artis, container, false)
    }

    private lateinit var articles: List<Article>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Contoh data
        articles = listOf(
            // (Sesuaikan dengan sumber daya gambar Anda)
            Article(
                R.drawable.chelsea,
                "Chelsea Islan",
                "Tak hanya memiliki paras yang cantik, Chelsea Islan juga mempunyai segudang prestasi",
                "Aktris muda cantik yang berprestasi serta baru saja dilamar oleh Rob Clinton ini merupakan seorang wanita keturunan Amerika Serikat. Memiliki wajah blasteran yang begitu cantik, tidak heran jika Chelsea Islan kemudian sukses masuk sebagai salah satu nominasi perempuan tercantik versi TC Candler selama tiga tahun berturut-turut, yaitu pada tahun 2018, 2019, dan 2020."
            ),
            Article(
                R.drawable.lyorda,
                "Lyorda Ginting",
                "Lyodra Ginting adalah salah satu aktris yang berhasil masuk ke dalam daftar 100 wajah tercantik pada tahun 2021 dari versi TC Candler",
                "Selain memiliki paras yang cantik, ia juga merupakan pemenang pada ajang pencarian bakat Indonesian Idol di tahun 2020. Setelah mengetahui dirinya masuk ke dalam daftar tersebut, Lyodra juga memberikan respons melalui unggahan pada akun instagramnya."
            ),
            Article(
                R.drawable.citra_kirana,
                "Citra Kirana",
                "Citra Kirana dikenal sebagai salah satu aktris papan atas Indonesia dengan wajah yang cantik.",
                "Citra Kirana, yang dikenal luas dengan nama Citra Kirana atau Ciki, adalah seorang aktris dan model berkebangsaan Indonesia. Debutnya di dunia akting dimulai dengan bermain dalam sinetron. Dengan bakat dan kecantikannya, Citra Kirana berhasil menarik perhatian banyak penggemar serta produser film. Selain itu, kehidupan pribadinya dengan Rezky Aditya, seorang aktor ternama, juga kerap menjadi sorotan media."
            ),
            Article(
                R.drawable.ayu_ting_ting,
                "Ayu Ting Ting",
                "Dikenal dengan suara khas dan pesona Jawa-nya, Ayu Ting Ting memikat banyak hati.",
                "Ayu Rosmalina, lebih dikenal dengan nama Ayu Ting Ting adalah seorang penyanyi dangdut, presenter, dan aktris Indonesia. Salah satu lagunya yang berjudul 'Alamat Palsu' menjadi hit besar di industri musik Indonesia."
            ),
            Article(
                R.drawable.raisa,
                "Raisa Andriana",
                "Raisa, dengan suara emasnya, telah menjadi salah satu diva pop Indonesia.",
                "Raisa Andriana adalah seorang penyanyi serta penulis lagu Indonesia. Dengan hits seperti 'Kali Kedua' dan 'LDR', ia memenangkan banyak penghargaan musik."
            ),
            Article(
                R.drawable.maudy,
                "Maudy Ayunda",
                "Maudy tidak hanya dikenal sebagai penyanyi, tetapi juga sebagai aktris muda berbakat.",
                "Maudy Ayunda adalah seorang penyanyi, aktris, dan aktivis pendidikan Indonesia. Ia juga dikenal sebagai mahasiswa lulusan dari salah satu universitas ternama di dunia, yaitu Oxford."
            ),
            Article(
                R.drawable.velove,
                "Velove Vexia",
                "Velove Vexia, aktris dengan wajah khas, menunjukkan kemampuan aktingnya dalam berbagai film.",
                "Velove Vexia Kaligis adalah seorang aktris Indonesia yang telah membintangi berbagai film layar lebar dan sinetron populer. Ia dikenal memiliki talenta akting yang luar biasa."
            ),
            Article(
                R.drawable.pevita_pearce,
                "Pevita Pearce",
                "Pevita Pearce memikat banyak orang dengan wajahnya yang cantik dan kemampuan aktingnya.",
                "Pevita Cleo Eileen Pearce adalah seorang aktris dan penyanyi Indonesia. Ia mulai dikenal setelah bermain dalam film 'Denias, Senandung di Atas Awan'."
            ),
            Article(
                R.drawable.sandrinna_michelle,
                "Sandrinna Michelle",
                "Sandrinna Michelle, bintang cilik yang tumbuh menjadi aktris muda berbakat.",
                "Sandrinna Michelle Skornicki adalah seorang aktris berkebangsaan Indonesia. Sejak kecil, ia sudah aktif di dunia hiburan dan berhasil memenangkan beberapa penghargaan akting."
            ),
            Article(
                R.drawable.wilona,
                "Natasha Wilona",
                "Dikenal lewat sinetron, Natasha Wilona kini menjadi salah satu aktris muda paling populer.",
                "Natasha Wilona adalah seorang aktris Indonesia yang mulai dikenal setelah bermain dalam sinetron 'Anak Jalanan'. Dengan talenta dan pesonanya, ia menjadi salah satu aktris muda paling dicari di industri hiburan."
            )
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.listArticle)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = ArticleAdapter(articles) { article ->
            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.sideDetail, ListDetailFragment.newInstance(article, ""))
                    .commit()
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frmFragment, ListDetailFragment.newInstance(article, ""))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListArtis.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListArtisFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
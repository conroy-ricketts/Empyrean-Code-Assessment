package com.example.empyreancodeassessment.features.itemDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.empyreancodeassessment.ECAApplication
import com.example.empyreancodeassessment.R
import com.example.empyreancodeassessment.ViewModelFactory
import javax.inject.Inject

class ItemDetailFragment : Fragment() {
    private lateinit var authorImageView: ImageView
    private lateinit var authorNameTextView: TextView
    private lateinit var authorEmailTextView: TextView
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ItemDetailViewModel>
    private val viewModel: ItemDetailViewModel by lazy {
        viewModelFactory.get<ItemDetailViewModel>(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ECAApplication.getAppComponent().inject(this)
        subscribeToViewModel()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_detail_fragment, container, false)
        recyclerView = view.findViewById(R.id.item_detail_comments_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        authorImageView = view.findViewById(R.id.item_detail_author_image)
        authorNameTextView = view.findViewById(R.id.item_detail_author_name)
        authorEmailTextView = view.findViewById(R.id.item_detail_author_email)

        return view
    }

    private fun subscribeToViewModel() {
        viewModel.author.observe(this) { author ->
            Log.d("ItemDetailFragment", "The author was successfully fetched!")
            authorImageView.setImageURI(author.avatar.toUri())
            authorNameTextView.text = "Author Name: " + author.name
            authorEmailTextView.text = "Author Email: " + author.email
        }
        viewModel.fetchAuthorError.observe(this) { errorMessage ->
            Log.e("ItemDetailFragment", "There was an error fetching the author!: $errorMessage")

            Toast.makeText(
                requireContext(),
                "There was an error fetching the author.",
                Toast.LENGTH_LONG
            ).show()
        }

        viewModel.itemComments.observe(this) { itemComments ->
            Log.d("ItemDetailFragment", "The item comments were successfully fetched!")
            recyclerView.adapter = ItemDetailCommentAdapter(itemComments)
        }
        viewModel.fetchItemCommentsError.observe(this) { errorMessage ->
            Log.e(
                "ItemDetailFragment",
                "There was an error fetching the item comments!: $errorMessage"
            )

            Toast.makeText(
                requireContext(),
                "There was an error fetching the item comments.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val TAG = "ItemDetailFragment"
    }
}
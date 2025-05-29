package com.example.empyreancodeassessment.features.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.empyreancodeassessment.ECAApplication
import com.example.empyreancodeassessment.R
import com.example.empyreancodeassessment.ViewModelFactory
import javax.inject.Inject

class FeedFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<FeedViewModel>
    private val viewModel: FeedViewModel by lazy {
        viewModelFactory.get<FeedViewModel>(requireActivity())
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
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    private fun subscribeToViewModel() {
        viewModel.feedItems.observe(this) { feedItems ->
            Log.d("FeedFragment", "The feed items were successfully fetched!")
        }
        viewModel.fetchFeedItemsError.observe(this) { errorMessage ->
            Log.e("FeedFragment", "There was an error fetching the feed items!: $errorMessage")

            Toast.makeText(
                requireContext(),
                "There was an error fetching the feed items.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val TAG = "FeedFragment"
    }
}
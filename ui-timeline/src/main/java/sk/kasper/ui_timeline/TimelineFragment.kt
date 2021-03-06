package sk.kasper.ui_timeline
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import sk.kasper.base.logger.Logger
import sk.kasper.ui_common.BaseFragment
import sk.kasper.ui_common.utils.createSlideAnimNavOptions
import sk.kasper.ui_timeline.ui.Timeline

@AndroidEntryPoint
class TimelineFragment : BaseFragment() {

    private val timelineViewModel: TimelineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModels()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Timeline(timelineViewModel)
            }
        }
    }

    private fun observeViewModels() {
        lifecycleScope.launchWhenStarted {
            timelineViewModel.sideEffects.collect {
                Logger.d("$it")
                when (it) {
                    SideEffect.ConnectionError -> {
                        Toast.makeText(requireView().context ,"A connection error occurred", Toast.LENGTH_SHORT).show()
                    }
                    is SideEffect.NavigateTo -> {
                        findNavController().navigate(
                            Uri.parse(it.uriString),
                            createSlideAnimNavOptions()
                        )
                    }
                    else -> {
                        // Nothing to do
                    }
                }
            }
        }
    }

}

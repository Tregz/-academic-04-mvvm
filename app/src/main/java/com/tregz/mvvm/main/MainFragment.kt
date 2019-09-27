package com.tregz.mvvm.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.tregz.mvvm.R
import com.tregz.mvvm.arch.bind.BindFactory
import com.tregz.mvvm.core.date.DateUtil
import com.tregz.mvvm.data.DataApple
import com.tregz.mvvm.list.ListApple
import kotlinx.android.synthetic.main.main_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment(), MainView {

    private val backend: MainBackend by lazy {
        // Inject MainView interface in ViewModel
        with(ViewModelProviders.of(this, BindFactory { MainBackend(this) })) {
            get(MainBackend::class.java)
        }
    }

    private var input: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListApple.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAppleCreated(backend.insertApple(Date(), R.color.colorAccent))
        onAppleCreated(backend.insertApple(DateUtil.addMonth(Date(), -1), android.R.color.white))
        onAppleCreated(backend.insertApple(null, R.color.colorPrimary))
    }

    override fun toast(message: String) {
        context?.let { Toast.makeText(it, message, Toast.LENGTH_SHORT).show() }
    }

    private fun onAppleCreated(apple: DataApple) {
        log.append(HtmlCompat.fromHtml("<b>Pomme ajoutée</b>", FROM_HTML_MODE_LEGACY))
        val skeleton = "d MMMM yyyy"
        val formatter = SimpleDateFormat(skeleton, Locale.getDefault())
        val day = apple.ripe?.let { formatter.format(it) }
        val unknown = "Non cueillie ou date de cueillette inconnue."
        val riped = if (day != null) "Cueillie le $day." else unknown
        log.append("\n" + riped + "\n")
        val total = "Taille de la liste: ${ListApple.listCount}"
        sum.text = total
    }

    companion object {
        fun newInstance() = MainFragment()
        private val TAG: String = MainFragment::class.java.simpleName
    }
}

package com.full.qr.scanner.top.secure.no.feature.tabs.create.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.full.qr.scanner.top.secure.no.R
import com.full.qr.scanner.top.secure.no.extension.isNotBlank
import com.full.qr.scanner.top.secure.no.extension.textString
import com.full.qr.scanner.top.secure.no.feature.tabs.create.BaseCreateBarcodeFragment
import com.full.qr.scanner.top.secure.no.model.schema.Mms
import com.full.qr.scanner.top.secure.no.model.schema.Schema
import kotlinx.android.synthetic.main.fragment_create_qr_code_mms.*

class CreateQrCodeMmsFragment : BaseCreateBarcodeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_qr_code_mms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitleEditText()
        handleTextChanged()
    }

    override fun showPhone(phone: String) {
        edit_text_phone.apply {
            setText(phone)
            setSelection(phone.length)
        }
    }

    override fun getBarcodeSchema(): Schema {
        return Mms(
            phone = edit_text_phone.textString,
            subject = edit_text_subject.textString,
            message = edit_text_message.textString
        )
    }

    private fun initTitleEditText() {
        edit_text_phone.requestFocus()
    }

    private fun handleTextChanged() {
        edit_text_phone.addTextChangedListener { toggleCreateBarcodeButton() }
        edit_text_subject.addTextChangedListener { toggleCreateBarcodeButton() }
        edit_text_message.addTextChangedListener { toggleCreateBarcodeButton() }
    }

    private fun toggleCreateBarcodeButton() {
        parentActivity.isCreateBarcodeButtonEnabled = edit_text_phone.isNotBlank() || edit_text_subject.isNotBlank() || edit_text_message.isNotBlank()
    }
}
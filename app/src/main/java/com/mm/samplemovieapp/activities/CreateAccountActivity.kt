package com.mm.samplemovieapp.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.mm.samplemovieapp.R
import com.mm.samplemovieapp.database.UserTable
import com.mm.samplemovieapp.databinding.ActivityRegisterBinding
import com.mm.samplemovieapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CreateAccountActivity : BaseActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var mViewModel : MainViewModel
    //calendar
    val cal = Calendar.getInstance()
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    companion object{
        fun newInstance(mContext : Context) : Intent{
            return Intent(mContext,CreateAccountActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initLayout()
        clickEvent()

    }

    private fun initLayout() {
        dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy hh:mm a"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)
        val textFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val inputDateStr = sdf.format(cal.time)
        val date: Date = inputFormat.parse(inputDateStr)!!
        binding.etDateOfBirth.text = textFormat.format(date)
    }

    private fun clickEvent() {
        binding.apply {
            btnCreateMyAccount.setOnClickListener {
                if(checkProcess()){
                    if(!isValidEmail(etEmailAddress.text.toString())){
                        Toast.makeText(this@CreateAccountActivity,"Please fill your correct email address.",Toast.LENGTH_SHORT).show()
                    }else if(etDateOfBirth.checkDataValid()){
                        Toast.makeText(this@CreateAccountActivity,"Please fill your birthday",Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(
                            this@CreateAccountActivity,
                            "Please check your register data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    mViewModel.insertUser(
                        UserTable(
                            0,
                            etFirstName.text.toString(),
                            etLastName.text.toString(),
                            etEmailAddress.text.toString(),
                            etDateOfBirth!!.text.toString(),
                            etNationality.text.toString(),
                            etCountry.text.toString(),
                            etPhone.text.toString()
                        )
                    )
                    Toast.makeText(this@CreateAccountActivity,"Register Successful",Toast.LENGTH_SHORT).show()
                    gotoMainActivity()
                }
            }

            ivBack.setOnClickListener {
                finish()
            }

            etDateOfBirth.setOnClickListener {
                callDatePickerDialog()
            }
        }
    }

    fun callDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
//        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
//            .setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryBlue))
//        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
//            .setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryBlue))
//        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).text =
//            getString(R.string.str_cancel)
//        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).text =
//            getString(R.string.str_ok)
    }

    private fun checkProcess() : Boolean {
        var b = false
        binding.apply {
            if(etFirstName.checkData()){
                b = true
            }
            if(etLastName.checkData()){
                b = true
            }

            if(!isValidEmail(etEmailAddress.text.toString())){
                b = true
            }
            if(etDateOfBirth.checkDataValid()){
                b = true
            }
            if(etNationality.checkData()){
                b = true
            }
            if(etCountry.checkData()){
                b = true
            }
            if(etPhone.checkData()){
                b = true
            }

        }

        return b
    }

    private fun EditText.checkData() : Boolean{
        return this.text.toString().isEmpty()
    }
    private fun TextView.checkDataValid() : Boolean{
        return this.text.toString().isEmpty()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun gotoMainActivity(){
        startActivity(MainActivity.newInstance(this))
        finish()
    }

}
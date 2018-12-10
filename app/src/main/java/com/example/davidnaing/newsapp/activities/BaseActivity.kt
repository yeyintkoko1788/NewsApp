package com.example.davidnaing.newsapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by yepyaesonetun on 7/18/18.
 **/
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayoutRes())
        setUpContents(savedInstanceState)
    }

    abstract fun getlayoutRes(): Int

    abstract fun setUpContents(savedInstanceState: Bundle?)




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
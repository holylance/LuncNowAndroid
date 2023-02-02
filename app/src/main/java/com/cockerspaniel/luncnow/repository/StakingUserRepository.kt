package com.cockerspaniel.luncnow.repository

import android.content.Context
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.screen.staking.model.StakingUsers
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

class StakingUserRepository(
    private val context: Context
) {

    private val gson = Gson()
    private var listUsers: StakingUsers = StakingUsers(emptyList())

    init {
        listUsers = gson.fromJson(
            getJsonStructure(),
            StakingUsers::class.java
        )
    }

    fun getUsers() = listUsers

    private fun getJsonStructure(): String {
        val stream = context.resources.openRawResource(R.raw.staking)
        val writer = StringWriter()
        val buffer = CharArray(1024)
        stream.use {
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }
}

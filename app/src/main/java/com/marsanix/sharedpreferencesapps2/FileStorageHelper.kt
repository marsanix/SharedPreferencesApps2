package com.marsanix.sharedpreferencesapps2

import android.content.Context

class FileStorageHelper {

    fun writeToFile(fileModel: FileModel, context: Context) {
        context.openFileOutput(fileModel.fileName, Context.MODE_PRIVATE).use {
            it.write(fileModel.data.toByteArray())
        }
    }

    fun readFromFile(fileName: String, context: Context): FileModel {
        val fileModel = FileModel()

        fileModel.fileName = fileName
        fileModel.data = context.openFileInput(fileName).bufferedReader().useLines { lines ->
            lines.fold("") {some, text ->
                "$some$text\n"
            }
        }

        return fileModel
    }

}
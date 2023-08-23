package com.ferdyfermadi.onepiececomposesubmission.data

import com.ferdyfermadi.onepiececomposesubmission.model.OnePiece
import com.ferdyfermadi.onepiececomposesubmission.model.OnePieceData

class OnePieceRepository {
    private val onePiece = mutableListOf<OnePiece>()

    init {
        if (onePiece.isEmpty()) {
            OnePieceData.pirate.forEach {
                onePiece.add(OnePiece(it.id, it.name, it.photo, it.description))
            }
        }
    }

    fun getPirate(): List<OnePiece> {
        return OnePieceData.pirate
    }

    fun searchPirate(query: String): List<OnePiece>{
        return OnePieceData.pirate.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getIdPirate(id: Int): OnePiece {
        return onePiece.first{
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: OnePieceRepository? = null

        fun getInstance(): OnePieceRepository =
            instance ?: synchronized(this) {
                OnePieceRepository().apply {
                    instance = this
                }
            }
    }
}
package com.bangkit.wisatabanten.data

import com.bangkit.wisatabanten.model.BantenTourism
import com.bangkit.wisatabanten.model.TourSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TourRepository {

    private val listTour = mutableListOf<BantenTourism>()

    init {
        if (listTour.isEmpty()) {
            TourSource.tours.forEach {
                listTour.add(BantenTourism(it, false))
            }
        }
    }


    fun getAllTour(): Flow<List<BantenTourism>> {
        return flowOf(listTour)
    }

    fun getTourById(tourId: String): Flow<BantenTourism> {
        return flowOf(
            listTour.first {
                it.tour.id == tourId
            }
        )
    }

    fun findTour(query: String): List<BantenTourism> {
        return listTour.filter {
            it.tour.title.contains(query, ignoreCase = true)
        }
    }


    companion object {
        @Volatile
        private var instance: TourRepository? = null

        fun getInstance(): TourRepository =
            instance ?: synchronized(this) {
                TourRepository().apply {
                    instance = this
                }
            }
    }
}
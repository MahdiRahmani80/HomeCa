package com.mehdi.rahmani.homeCa.model.makeFakeData

import com.mehdi.rahmani.homeCa.model.dataBase.AppDatabase
import com.mehdi.rahmani.homeCa.model.objects.*
import kotlin.random.Random

class MakeFakeData {

    companion object{
        fun mkRandomHome(dataBase: AppDatabase) {

            val cityList = ArrayList<City>()
            val neighbourList = ArrayList<Neighbourhood>()

            //add city
            for (i in (0..2)) {
                var city: String?
                if (i == 0) {
                    city = "تهران"
                } else if (i == 1) {
                    city = "تبریز"
                } else {
                    city = "ارومیه"
                }
                val c = City(i, city)
                cityList.add(c)
                dataBase.HomeDao().addCity(c)
            }
            // add neighbour

            for (i in (0..2)) {
                val n = Neighbourhood(i, "محله $i")
                neighbourList.add(n)
                dataBase.HomeDao().addNeighbour(n)
            }

            // add home
            for (i in (0..300)) {
                val randYear = Random.nextInt(1995, 2022)
                val doc = if (randYear % 2 == 0) true else false
                val area = randYear * i / 1000
                val h = Home(i, randYear, doc, area, area * i * randYear)
                dataBase.HomeDao().addHome(h)
                dataBase.HomeDao().addHomeInNeighbour(HomesInNeighbour(i, h, neighbourList[i % 3]))
                dataBase.HomeDao()
                    .addNeighbourInCity(NeighbourInCity(i, neighbourList[i % 3], cityList[i % 3]))
            }

        }

    }
}
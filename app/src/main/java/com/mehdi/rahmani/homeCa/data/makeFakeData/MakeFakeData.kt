package com.mehdi.rahmani.homeCa.data.makeFakeData

import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.model.*
import kotlin.random.Random

class MakeFakeData {

    companion object {

        fun mkRandomHome(dataBase: HomeDao) {

            val cityList = ArrayList<City>()
            val neighbourList = ArrayList<Neighbourhood>()

            //add city
            for (i in (0..2)) {
                val city = when (i) {
                    0 -> "تهران"
                    1 -> "تبریز"
                    else -> "ارومیه"
                }
                val c = City(i, city)
                cityList.add(c)
                dataBase.addCity(c)
            }
            // add neighbour

            for (n in (0..2)) {
                val n = Neighbourhood(n, "محله ${n+1}")
                neighbourList.add(n)
                dataBase.addNeighbour(n)
            }

            // add home
            for (i in (0..300)) {
                val randYear = Random.nextInt(1995, 2022)
                val doc = (randYear % 2 == 0)
                val area = Random.nextInt(50,1000)
                val h = Home(i, randYear, doc, area, Random.nextInt(100,1000))
                dataBase.addHome(h)
                dataBase.addHomeInNeighbour(HomesInNeighbour(i, h, neighbourList[i % 3]))
                dataBase.addNeighbourInCity(
                    NeighbourInCity(
                        i,
                        neighbourList[i % 3],
                        cityList[i % 3]
                    )
                )
            }

        }

    }
}
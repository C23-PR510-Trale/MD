package com.example.tralecapstone.model

import com.example.tralecapstone.R

object FakePlanTripDataSource {
    val dummyTrip = listOf(
        PlanTrip(
            1,
            R.drawable.ic_launcher_background,
            "Token Sertifikasi Associate Android Developer",
            6000,
            3.5,
            "Landmark",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 11", R.drawable.ic_launcher_background, "desc 11", 70000),
                Trips(1, "Tugu Pahlawan 12", R.drawable.ic_launcher_background, "desc 12", 70000),
                Trips(2, "Tugu Pahlawan 13", R.drawable.ic_launcher_background, "desc 13", 70000),
            ),
            Facilities(1, true, true, true),
        ),
        PlanTrip(
            2,
            R.drawable.ic_launcher_background,
            "Token Sertifikasi TensorFlow",
            4500,
            4.3,
            "Nature",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 21", R.drawable.ic_launcher_background, "desc 21", 70000),
                Trips(1, "Tugu Pahlawan 22", R.drawable.ic_launcher_background, "desc 22", 70000),
                Trips(2, "Tugu Pahlawan 23", R.drawable.ic_launcher_background, "desc 23", 70000),
            ),
            facilities = Facilities(2, true, false, true),
        ),
        PlanTrip(
            3,
            R.drawable.ic_launcher_background,
            "Paket Langganan Dicoding Academy 30 Hari",
            1000,
            3.6,
            "Landmark",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 31", R.drawable.ic_launcher_background, "desc 31", 70000),
                Trips(1, "Tugu Pahlawan 32", R.drawable.ic_launcher_background, "desc 32", 70000),
                Trips(2, "Tugu Pahlawan 33", R.drawable.ic_launcher_background, "desc 33", 70000),
            ),
            facilities = Facilities(3, true, true, false),

            ),
        PlanTrip(
            4,
            R.drawable.ic_launcher_background,
            "Jaket Hoodie Dicoding",
            1000,
            3.2,
            "Culture",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 41", R.drawable.ic_launcher_background, "desc 41", 70000),
                Trips(1, "Tugu Pahlawan 42", R.drawable.ic_launcher_background, "desc 42", 70000),
                Trips(2, "Tugu Pahlawan 43", R.drawable.ic_launcher_background, "desc 43", 70000),
            ),
            facilities = Facilities(4, false, false, true),

            ),
        PlanTrip(
            5,
            R.drawable.ic_launcher_background,
            "Tas Ransel Dicoding",
            1000,
            4.1,
            "Culinary",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 51", R.drawable.ic_launcher_background, "desc 51", 70000),
                Trips(1, "Tugu Pahlawan 52", R.drawable.ic_launcher_background, "desc 52", 70000),
                Trips(2, "Tugu Pahlawan 53", R.drawable.ic_launcher_background, "desc 53", 70000),
            ),
            facilities = Facilities(5, false, true, false),

            ),
        PlanTrip(
            6,
            R.drawable.ic_launcher_background,
            "Google Play Store Voucher Code",
            1000,
            4.7,
            "Culinary",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 61", R.drawable.ic_launcher_background, "desc 61", 70000),
                Trips(1, "Tugu Pahlawan 62", R.drawable.ic_launcher_background, "desc 62", 70000),
                Trips(2, "Tugu Pahlawan 63", R.drawable.ic_launcher_background, "desc 63", 70000),
            ),
            facilities = Facilities(6, false, false, true),

            ),
        PlanTrip(
            7, R.drawable.ic_launcher_background, "Polo Shirt Dicoding", 750, 3.9, "Nature", "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 71", R.drawable.ic_launcher_background, "desc 71", 70000),
                Trips(1, "Tugu Pahlawan 72", R.drawable.ic_launcher_background, "desc 72", 70000),
                Trips(2, "Tugu Pahlawan 73", R.drawable.ic_launcher_background, "desc 73", 70000),
            ),
            facilities = Facilities(7, true, true, true),

            ),
        PlanTrip(
            8,
            R.drawable.ic_launcher_background,
            "Tumbler Digital Dicoding",
            750,
            2.6,
            "Culture",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 81", R.drawable.ic_launcher_background, "desc 81", 70000),
                Trips(1, "Tugu Pahlawan 82", R.drawable.ic_launcher_background, "desc 82", 70000),
                Trips(2, "Tugu Pahlawan 83", R.drawable.ic_launcher_background, "desc 83", 70000),
            ),
            facilities = Facilities(8, true, true, true),
        ),
        PlanTrip(
            9,
            R.drawable.ic_launcher_background,
            "T-shirt Dicoding Developer Biru",
            300,
            2.5,
            "Landmark",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 91", R.drawable.ic_launcher_background, "desc 91", 70000),
                Trips(1, "Tugu Pahlawan 92", R.drawable.ic_launcher_background, "desc 92", 70000),
                Trips(2, "Tugu Pahlawan 93", R.drawable.ic_launcher_background, "desc 93", 70000),
            ),
            facilities = Facilities(9, false, false, true),
        ),
        PlanTrip(
            10,
            R.drawable.ic_launcher_background,
            "T-shirt Dicoding Champ",
            300,
            3.0,
            "Nature",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 101", R.drawable.ic_launcher_background, "desc 101", 70000),
                Trips(1, "Tugu Pahlawan 102", R.drawable.ic_launcher_background, "desc 102", 70000),
                Trips(2, "Tugu Pahlawan 103", R.drawable.ic_launcher_background, "desc 103", 70000),
            ),
            facilities = Facilities(10, true, false, true),

            ),
        PlanTrip(
            11,
            R.drawable.ic_launcher_background,
            "T-shirt Dicoding Graduate",
            300,
            4.1,
            "Culture",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 111", R.drawable.ic_launcher_background, "desc 111", 70000),
                Trips(1, "Tugu Pahlawan 112", R.drawable.ic_launcher_background, "desc 112", 70000),
                Trips(2, "Tugu Pahlawan 113", R.drawable.ic_launcher_background, "desc 113", 70000),
            ),
            facilities = Facilities(11, true, true, true),

            ),
        PlanTrip(
            12,
            R.drawable.ic_launcher_background,
            "T-shirt Dicoding Developer Putih",
            300,
            2.1,
            "Nature",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 121", R.drawable.ic_launcher_background, "desc 121", 70000),
                Trips(1, "Tugu Pahlawan 122", R.drawable.ic_launcher_background, "desc 122", 70000),
                Trips(2, "Tugu Pahlawan 123", R.drawable.ic_launcher_background, "desc 123", 70000),
            ),
            facilities = Facilities(12, false, false, true),

            ),
        PlanTrip(
            13,
            R.drawable.ic_launcher_background,
            "T-shirt Dicoding Developer Putih",
            300,
            4.7,
            "Landmark",
            "Open",
            trips = listOf(
                Trips(0, "Tugu Pahlawan 131", R.drawable.ic_launcher_background, "desc 131", 70000),
                Trips(1, "Tugu Pahlawan 132", R.drawable.ic_launcher_background, "desc 132", 70000),
                Trips(2, "Tugu Pahlawan 133", R.drawable.ic_launcher_background, "desc 133", 70000),
            ),
            facilities = Facilities(13, true, true, false),

            ),
    )
}
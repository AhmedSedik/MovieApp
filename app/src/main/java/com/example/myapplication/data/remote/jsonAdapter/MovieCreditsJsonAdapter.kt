package com.example.myapplication.data.remote.jsonAdapter

import com.example.myapplication.data.model.domain.CreditsDomain
import com.example.myapplication.data.remote.Credits
import com.squareup.moshi.FromJson

class MovieCreditsJsonAdapter {

    @FromJson
    fun fromJson(json: Credits?): CreditsDomain? {
        if (json?.id == null) return null

        return CreditsDomain(
            json.id,
            json.casts.map {
                CreditsDomain.Cast(
                    it.adult,
                    it.gender,
                    it.id,
                    it.knownForDepartment,
                    it.name,
                    it.originalName,
                    it.popularity,
                    it.profilePath,
                    it.castId,
                    it.character,
                    it.creditId,
                    it.order,
                )
            },
            json.crews.map {
                CreditsDomain.Crew(
                    it.adult,
                    it.gender,
                    it.id,
                    it.knownForDepartment,
                    it.name,
                    it.originalName,
                    it.popularity,
                    it.profilePath,
                    it.creditId,
                    it.department,
                    it.job,
                )
            },
        )
    }
}
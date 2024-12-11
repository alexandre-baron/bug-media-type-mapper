package com.acme

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.Response
import java.net.URI

private const val s = "application/vnd.acme.v1+json"

@ApplicationScoped
@Path("/api/versions")
class VersioningResource {

    @GET
    @Path("/{id}")
    fun get(
        @PathParam("id") id: String
    ): Version {
        return Version(id)
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_ACME_V1)
    fun get1(
        @PathParam("id") id: String
    ): Version1 {
        return Version1(id)
    }

    @POST
    @Path("/search")
    fun search(
        search: Version,
        @QueryParam("pageSize") pageSize: Int
    ): List<Version> {
        return listOf(
            Version("1"),
            Version("2"),
            Version("3")
        )
    }

    @POST
    @Path("/search")
    @Consumes(APPLICATION_ACME_V1)
    @Produces(APPLICATION_ACME_V1)
    fun search1(
        search: Version1,
        @QueryParam("pageSize") pageSize: Int
    ): List<Version1> {
        return listOf(
            Version1("1"),
            Version1("2"),
            Version1("3")
        )
    }

    @POST
    fun create(
        body: Version
    ): Response {
        return Response.created(URI.create("/api/version/${body.id}")).build()
    }

    @POST
    @Consumes(APPLICATION_ACME_V1)
    fun create1(
        body: Version1
    ): Response {
        return Response.created(URI.create("/api/version/${body.name}")).build()
    }

    companion object     {
        const val APPLICATION_ACME_V1 = "application/vnd.acme.v1+json"
    }
}

data class Version(
    val id: String
)

data class Version1(
    val name: String
)

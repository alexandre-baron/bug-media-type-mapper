package com.acme

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.PreMatching
import jakarta.ws.rs.ext.Provider

@Provider
@PreMatching
class VersionFilter : ContainerRequestFilter {
    override fun filter(requestContext: ContainerRequestContext) {
        val versionParam = requestContext.uriInfo.queryParameters.get("version")
        if (versionParam != null) {
            if (versionParam.first().equals("1")) {
                if (requestContext.getHeaderString("Accept") != null) {
                    // Accept header update
                    requestContext.headers.replace("Accept", listOf("application/vnd.acme.v1+json"))
                }
                if (requestContext.getHeaderString("Content-Type") != null) {
                    // Content-Type header update
                    requestContext.headers.replace("Content-Type", listOf("application/vnd.acme.v1+json"))
                }
            }
        }
    }
}

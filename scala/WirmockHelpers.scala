import cats.syntax.eq._
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.http.Request
import com.github.tomakehurst.wiremock.matching.MatchResult
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import play.api.http.Status
import play.api.libs.json.{Format, Json}

object WireMockHelpers {

  /**
   * Useful wiremock helper to verify that the request body serialises to what we expect.
   * Hint: If it's not working and you can't work out why, check what A you are passing in... :)
   * @param url: String
   * @param format: play.api.libs.json.Format as an implicit
   * @tparam A: Model we want to get format for
   */
  def verifyWithBodyParse[A](url: String)(implicit format: Format[A]): Unit = verify(
    postRequestedFor(urlPathEqualTo(url))
      .andMatching((value: Request) => customValueMatcher(url, value))
  )

  /**
   * Same as above, but it also compares a Json serialised expected A with the wiremock request
   */
  def verifyWithBodyParse[A](url: String, expected: A)(implicit format: Format[A]): Unit = verify(
    postRequestedFor(urlPathEqualTo(url))
      .andMatching((value: Request) => customValueMatcher(url, value))
      .withRequestBody(equalToJson(Json.toJson(expected).toString()))
  )

  /**
   * Same as above, but overloaded with headers to check
   */
  def verifyWithBodyParse[A](url: String, headers: (String, String))(implicit format: Format[A]): Unit = verify(
    postRequestedFor(urlPathEqualTo(url))
      .withHeader(headers._1, equalTo(headers._2))
      .andMatching((value: Request) => customValueMatcher(url, value))
  )

  /**
   * Same as above, but overloaded with headers to check and also compares a Json serialised expected A with the wiremock request
   */
  def verifyWithBodyParse[A](url: String, headers: (String, String), expected: A)(implicit format: Format[A]): Unit = verify(
    postRequestedFor(urlPathEqualTo(url))
      .withHeader(headers._1, equalTo(headers._2))
      .andMatching((value: Request) => customValueMatcher(url, value))
      .withRequestBody(equalToJson(Json.toJson(expected).toString()))
  )

  /**
   * Helper method to attempt to parse request body into type, using asOpt.
   * If option is empty, it can't and wiremock verify should fail, else wiremock verify is successful.
   */
  private def customValueMatcher[A](url: String, request: Request)(implicit format: Format[A]): MatchResult =
    MatchResult.of(request.getUrl === url && Json.parse(request.getBodyAsString).asOpt[A].nonEmpty)

  def stubForPostNoResponseBody(url: String, responseStatus: Int = Status.OK): StubMapping = stubFor(
    post(urlPathEqualTo(url)).willReturn(
      aResponse()
        .withStatus(responseStatus)
    )
  )

  def stubForPostWithResponseBody(url: String, jsonBody: String, responseStatus: Int = Status.OK): StubMapping = stubFor(
    post(urlPathEqualTo(url)).willReturn(
      aResponse()
        .withStatus(responseStatus)
        .withBody(jsonBody)
    )
  )

  def stubForPostWithRequestBodyMatching(url: String, requestMatchingPath: String, jsonBody: String, responseStatus: Int = Status.OK): StubMapping = stubFor(
    post(urlPathEqualTo(url))
      .withRequestBody(matchingJsonPath(requestMatchingPath))
      .willReturn(
        aResponse()
          .withStatus(responseStatus)
          .withBody(jsonBody)
      )
  )

}

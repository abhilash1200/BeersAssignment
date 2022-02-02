import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.SoftAssertions;

public class Base {

    public RequestSpecification beerRequestSpec(){
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri("https://api.punkapi.com/")
                .basePath("v2/beers");
        return requestSpecification;
    }

    public ResponseSpecification beerResponseSpec(){
        ResponseSpecification responseSpecification = RestAssured.expect().statusCode(200);
        return responseSpecification;
    }

    public void commonBeerAssertions(Response response){
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).isEqualTo(200);
        softly.assertThat(response.body().jsonPath().getString("[0].id")).isNotNull();
        softly.assertThat(response.body().jsonPath().getString("[0].name")).isNotNull();
        softly.assertThat(response.body().jsonPath().getString("[0].abv")).isNotNull();
        softly.assertAll();

    }

}

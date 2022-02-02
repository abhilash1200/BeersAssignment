import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;


public class BeersApiAutomation extends Base  {


    @Test(priority = 1)
    public void getAllBeers() {

        Response response = given()
                .spec(beerRequestSpec())
                .when()
                .get()
                .then().spec(beerResponseSpec()).extract().response();
        commonBeerAssertions(response);
    }
    @Test(priority = 2)
    public void getAllBeersBrewedBeforeACertainDate(){
        Response response = given()
                .spec(beerRequestSpec())
                .queryParam("brewed_before", "11-2012")
                .when()
                .get()
                .then()
                .spec(beerResponseSpec())
                .extract().response();
        commonBeerAssertions(response);
    }
    @Test(priority = 3)
    public void getAllBeersbrewedWhichHaveAbvGreaterThan6() {
        Response response = given()
                .spec(beerRequestSpec())
                .queryParam("abv_gt", "7")
                .when()
                .get()
                .then()
                .spec(beerResponseSpec())
                .extract().response();
        commonBeerAssertions(response);
    }
    @Test(priority = 4)
    public void verifyIfPaginationIsWorkingAsExpected() {
        Response response = given()
                .spec(beerRequestSpec())
                .queryParam("page", "2")
                .queryParam("per_page", "5")
                .when()
                .get()
                .then()
                .spec(beerResponseSpec())
                .extract().response();
        assertThat(response.jsonPath().getInt("size")).isEqualTo(5);
        commonBeerAssertions(response);

    }



}

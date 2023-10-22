package com.bastiaanjansen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "api-versioning.type=param",
        "api-versioning.param=api_version"
})
@AutoConfigureMockMvc
public class ParamVersioningTypeIT {

    @Autowired
    private MockMvc mvc;

    @ParameterizedTest
    @DisplayName("Executes correct method for different versions")
    @ValueSource(strings = { "1", "2" })
    void test_returnsCorrectResponse(String version) throws Exception {
        String expectedResponse = String.format("Version %s", version);

        RequestBuilder request = get(Controller.ENDPOINT)
                .queryParam("api_version",  version);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedResponse)));
    }

    @Test
    @DisplayName("Returns not found when version is unknown")
    void test_unknownVersion_returnsNotFound() throws Exception {
        RequestBuilder request = get(Controller.ENDPOINT)
                .queryParam("api_version",  "3");

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Returns not found when query param is unknown")
    void test_unknownVersionHeader_returnsNotFound() throws Exception {
        RequestBuilder request = get(Controller.ENDPOINT)
                .queryParam("api-version",  "3");

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

}

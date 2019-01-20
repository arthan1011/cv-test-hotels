package org.arthan.hotels.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotelResourceTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldSaveHotel() throws Exception {

        Gson gson = new Gson();

        JsonObject newHotelJson = new JsonObject();
        newHotelJson.addProperty("name", "King Solomon Hotel");
        newHotelJson.addProperty("catid", "HC154963ZZ");
        newHotelJson.addProperty("img", "/b3247259d5dd4989a55b326edebef7e78db5626b79cc19daf15baceabedc8552.jpg");
        newHotelJson.addProperty("addr", "155-159 Golders Green Rd, London NW11 9BX");

        JsonArray coordinates = new JsonArray();
        coordinates.add(51.575276d);
        coordinates.add(-0.204098d);
        newHotelJson.add("point", coordinates);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(newHotelJson));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.notNullValue()));
    }

    @Test
    public void shouldSaveAndReturnHotel() throws Exception {
        Gson gson = new Gson();

        JsonObject newHotelJson = new JsonObject();
        String name = "King Nero Hotel";
        String catalogId = "NC154963ZZ";
        String image = "/b3247259d5dd4989a55b326edebef7e78db5626b79cc19daf15e23edc8552.jpg";
        String address = "155-159 Golders Red Rd, London NW11 9BX";
        newHotelJson.addProperty("name", name);
        newHotelJson.addProperty("catid", catalogId);
        newHotelJson.addProperty("img", image);
        newHotelJson.addProperty("addr", address);

        JsonArray coordinates = new JsonArray();
        double firstCoordinate = 51.575276d;
        double secondCoordinate = -0.204098d;
        coordinates.add(firstCoordinate);
        coordinates.add(secondCoordinate);
        newHotelJson.add("point", coordinates);

        JsonObject site = new JsonObject();
        String siteLabel = "www.kingnerohotel.com";
        String siteUrl = "http://www.kingnerohotel.com/";
        site.addProperty("label", siteLabel);
        site.addProperty("url", siteUrl);
        newHotelJson.add("site", site);

        JsonArray services = new JsonArray();
        String firstService = "restaurant";
        String secondService = "fitness";
        services.add(firstService);
        services.add(secondService);
        newHotelJson.add("services", services);

        MockHttpServletRequestBuilder saveRequest = MockMvcRequestBuilders.post("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(newHotelJson));

        MvcResult saveResult = mockMvc.perform(saveRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String hotelId = saveResult.getResponse().getContentAsString();

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/api/hotel/" + hotelId)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(getRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("catid", Matchers.is(catalogId)))
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("addr", Matchers.is(address)))
                .andExpect(MockMvcResultMatchers.jsonPath("img", Matchers.is(image)))
                .andExpect(MockMvcResultMatchers.jsonPath("site.label", Matchers.is(siteLabel)))
                .andExpect(MockMvcResultMatchers.jsonPath("site.url", Matchers.is(siteUrl)))
                .andExpect(MockMvcResultMatchers.jsonPath("services[0]", Matchers.is(firstService)))
                .andExpect(MockMvcResultMatchers.jsonPath("services[1]", Matchers.is(secondService)))
                .andExpect(MockMvcResultMatchers.jsonPath("point[0]", Matchers.is(firstCoordinate)))
                .andExpect(MockMvcResultMatchers.jsonPath("point[1]", Matchers.is(secondCoordinate)));

    }

    @Test
    public void shouldDeleteHotel() throws Exception {
        Gson gson = new Gson();

        JsonObject newHotelJson = new JsonObject();
        String name = "King Richard Hotel";
        String catalogId = "RC154963ZZ";
        String image = "/b3247259d5dd4989a55b326edebef7e78323b79cc19daf15e23edc8552.jpg";
        String address = "155-159 Golders Blue Rd, London NW11 9BX";
        newHotelJson.addProperty("name", name);
        newHotelJson.addProperty("catid", catalogId);
        newHotelJson.addProperty("img", image);
        newHotelJson.addProperty("addr", address);

        JsonArray coordinates = new JsonArray();
        double firstCoordinate = 51.575276d;
        double secondCoordinate = -0.204098d;
        coordinates.add(firstCoordinate);
        coordinates.add(secondCoordinate);
        newHotelJson.add("point", coordinates);

        JsonObject site = new JsonObject();
        String siteLabel = "www.kingrichardhotel.com";
        String siteUrl = "http://www.kingrichardhotel.com/";
        site.addProperty("label", siteLabel);
        site.addProperty("url", siteUrl);
        newHotelJson.add("site", site);

        JsonArray services = new JsonArray();
        String firstService = "restaurant";
        String secondService = "fitness";
        services.add(firstService);
        services.add(secondService);
        newHotelJson.add("services", services);

        MockHttpServletRequestBuilder saveRequest = MockMvcRequestBuilders.post("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(newHotelJson));

        MvcResult saveResult = mockMvc.perform(saveRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String hotelId = saveResult.getResponse().getContentAsString();

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/api/hotel/" + hotelId)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(getRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("catid", Matchers.is(catalogId)));

        MockHttpServletRequestBuilder deleteRequest = MockMvcRequestBuilders.delete("/api/hotel/" + hotelId);

        mockMvc.perform(deleteRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(getRequest)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
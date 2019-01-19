package org.arthan.hotels.rest;

import com.google.gson.Gson;
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

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(newHotelJson));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.notNullValue()));
    }
}
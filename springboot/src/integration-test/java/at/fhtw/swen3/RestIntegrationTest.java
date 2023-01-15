package at.fhtw.swen3;


import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private String[] codes = {"WTTA069", "WENB01", "WTTA030"};


    public String submit_parcel() throws Exception {
        MvcResult result = mockMvc.perform(post("/parcel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"weight\": 20,\n" +
                                "  \"recipient\": {\n" +
                                "    \"name\": \"Malte\",\n" +
                                "    \"street\": \"Matzleinsdorfer Platz 4\",\n" +
                                "    \"postalCode\": \"A-1200\",\n" +
                                "    \"city\": \"Vienna\",\n" +
                                "    \"country\": \"Austria\"\n" +
                                "  },\n" +
                                "  \"sender\": {\n" +
                                "    \"name\": \"ok\",\n" +
                                "    \"street\": \"Gerhardusgasse 21\",\n" +
                                "    \"postalCode\": \"A-1200\",\n" +
                                "    \"city\": \"Vienna\",\n" +
                                "    \"country\": \"Austria\"\n" +
                                "  }\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andReturn();

        return result.getResponse().getContentAsString().split("\"")[3];
    }

    public void getParcelWithTrackingID(String trackingID) throws Exception{
        mockMvc.perform(get("/parcel/" + trackingID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                    .andExpect(status().isOk());
    }

    public void reportHopArrival(String trackingID, String code) throws Exception{
        mockMvc.perform(post("/parcel/" + trackingID + "/reportHop/" + code)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }

    private void reportFinalDelivery(String trackingID) throws Exception {
        mockMvc.perform(post("/parcel/" + trackingID + "/reportDelivery/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }


    @Test
    public void parcel_journey() throws Exception {
        String trackingID = submit_parcel();
        getParcelWithTrackingID(trackingID);
        reportHopArrival(trackingID, codes[0]);
        getParcelWithTrackingID(trackingID);
        reportHopArrival(trackingID, codes[1]);
        getParcelWithTrackingID(trackingID);
        reportHopArrival(trackingID, codes[2]);
        reportFinalDelivery(trackingID);
        getParcelWithTrackingID(trackingID);
    }

}

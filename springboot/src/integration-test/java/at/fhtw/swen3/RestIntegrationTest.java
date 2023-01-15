package at.fhtw.swen3;


import org.junit.jupiter.api.Test;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public String submit_parcel() throws Exception {

        MvcResult result =mockMvc.perform(post("/parcel")
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

        return  result.getResponse().getContentAsString().split("\"")[3];
    }

    @Test
    public void transition_parcel() throws Exception {

        mockMvc.perform(post("/parcel/PYJRB4HZ6")
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
                .andExpect(status().isOk());
    }

    @Test
    public void track_parcel() throws Exception {

        mockMvc.perform(get("/parcel/PYJRB4HZ6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }

    @Test
    public void post_warehouse() throws Exception {
        // Read the JSON file
        File file = ResourceUtils.getFile("classpath:test.json");
        String json = new String(Files.readAllBytes(file.toPath()));

        // Send the POST request and verify the response
        mockMvc.perform(post("/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void get_warehouse() throws Exception {
        // Send the GET request and verify the response
        mockMvc.perform(get("/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }


}

package shaimaa.tinyurl.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import shaimaa.tinyurl.api.controllers.UrlController;
import shaimaa.tinyurl.api.dto.UrlCreateDTO;
import shaimaa.tinyurl.api.dto.UrlDTO;
import shaimaa.tinyurl.domain.Url;
import shaimaa.tinyurl.domain.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;
import java.util.Optional;

@WebMvcTest(controllers = UrlController.class)
class UrlControllerTest {
    @MockBean
    private UrlService mockUrlRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenGivenShortUrl_thenRedirectToOriginalUrl() throws Exception {
        // GIVEN
        String shortUrl = "HQ4K15";
        String originalUrl = "originalUrl";
        Url url = new Url(shortUrl, originalUrl, 1660723090);
        Mockito.when(mockUrlRepository.getByShortUrl(shortUrl)).thenReturn(Optional.of(url));

        // WHEN
        RequestBuilder request = MockMvcRequestBuilders.get("/{shortUrl}", shortUrl);
        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();

        // THEN
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(mockUrlRepository).getByShortUrl(shortUrl);
    }

    @Test
    void whenGivenOriginalUrl_thenReturnShortUrl() throws Exception {
        // GIVEN
        String originalUrl = "originalUrl";
        String shortUrl = "shortUrl";
        Url url = new Url(shortUrl, originalUrl, 1660723090);
        Mockito.when(mockUrlRepository.createUrl(originalUrl)).thenReturn(url);

        // WHEN
        Map<String, String> requestBody = Map.of("originalUrl", originalUrl);
        RequestBuilder request = MockMvcRequestBuilders.post("/urls")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();

        // THEN
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        UrlDTO urlDTO = objectMapper.readValue(response.getContentAsString(), UrlDTO.class);
        Assertions.assertEquals(originalUrl, urlDTO.getOriginalUrl());
        Assertions.assertEquals(shortUrl, urlDTO.getShortUrl());
        Mockito.verify(mockUrlRepository).createUrl(ArgumentMatchers.any());
    }
}

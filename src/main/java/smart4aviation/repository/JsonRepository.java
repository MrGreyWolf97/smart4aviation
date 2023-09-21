package smart4aviation.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@AllArgsConstructor
public abstract class JsonRepository<T> {

    private String mockPath;
    private TypeReference<List<T>> typeReference;
    private ObjectMapper objectMapper;

    public List<T> findAll() throws IOException {
        return findAllByPathAndType(mockPath, typeReference);
    }

    private List<T> findAllByPathAndType(String path, TypeReference<List<T>> typeReference) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        String staticDataString = IOUtils.toString(classPathResource.getInputStream(), StandardCharsets.UTF_8);
        return objectMapper.readValue(staticDataString, typeReference);
    }

}
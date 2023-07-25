package com.example.blog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {
    //private static final Map<Integer, Blog> comentarios = new HashMap<>();
    private static final String FILE_PATH = "/home/dxeox/Desarrollos/Ejercicios/BlogJava/blog/data.json";

    @PostMapping("/blog")
    public String addCustomer(@RequestBody Blog comentario) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Blog> miLista = new ArrayList<>();
        try {

            // Lee el archivo JSON y convierte su contenido a una lista de Documentos
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            int id_new= comments.size() + 1;
            comentario.setNivel(0);
            comentario.setPuntos(0);
            comentario.setId_comentario(id_new);
            comentario.setAnswers(miLista);

            // Agrega el nuevo documento a la lista
            comments.add(comentario);

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Documento agregado exitosamente al archivo JSON.";
    }

    @GetMapping("/blog")
    public List<Blog> getBlogList() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Blog> comments = null;
        try{
            // Lee el archivo JSON y convierte su contenido a una lista de Documentos
            comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
        }catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return comments;
    }


    @PostMapping("/blog/comment/{id}")
    public String addCommentToBlog(@PathVariable Integer id, @RequestBody Blog comentario) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Blog> miLista = new ArrayList<>();
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            int id_new;
            comentario.setNivel(1);
            comentario.setAnswers(miLista);

            // Busca el comentario padre (id_comentario) en la lista de comments
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    // Agrega el comentario hijo al campo "answers" del comentario padre
                    id_new= blog.getAnswers().size() + 1;
                    comentario.setId_comentario(id_new);
                    blog.getAnswers().add(comentario);
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

            System.out.println("Comentario agregado exitosamente al campo 'answers' del JSON.");

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

        return "Comentario agregado exitosamente al campo 'answers' del JSON.";
    }

    @PostMapping("/blog/comment/{id}/{id2}")
    public String addComment1ToBlog(@PathVariable Integer id, @PathVariable Integer id2, @RequestBody Blog comentario) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            int id_new= 0;
            comentario.setNivel(2);

            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    // Encuentra el comentario de segundo nivel en la lista de answers
                    for (Blog answer : blog.getAnswers()) {
                        if (answer.getId_comentario().equals(id2)) {
                            // Agrega el comentario al campo de segundo nivel
                            answer.getAnswers().add(comentario);
                            break;
                        }
                    }
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

        return "Comentario agregado exitosamente";
    }

    @PostMapping("/puntos/add/{id}")
    public String addPointsLevel0(@PathVariable Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();
        int new_puntos ;
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    new_puntos = blog.getPuntos()+1;
                    blog.setPuntos(new_puntos);
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Puntos agregados con éxito";
    }

    @PostMapping("/puntos/add/{id}/{id1}")
    public String addPointsLevel1(@PathVariable Integer id, @PathVariable Integer id1) {
        ObjectMapper objectMapper = new ObjectMapper();
        int new_puntos;
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    // Encuentra el comentario de segundo nivel en la lista de answers
                    for (Blog answer : blog.getAnswers()) {
                        if (answer.getId_comentario().equals(id1)) {
                            new_puntos = answer.getPuntos()+1;
                            answer.setPuntos(new_puntos);
                            break;
                        }
                    }
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Puntos agregados con éxito";
    }

    @PostMapping("/puntos/add/{id}/{id1}/{id2}")
    public String addPointsLevel2(@PathVariable Integer id, @PathVariable Integer id1, @PathVariable Integer id2) {
        ObjectMapper objectMapper = new ObjectMapper();
        int new_puntos ;
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    // Encuentra el comentario de segundo nivel en la lista de answers
                    for (Blog answer : blog.getAnswers()) {
                        if (answer.getId_comentario().equals(id1)) {
                            // Encuentra el comentario de segundo nivel en la lista de answers
                            for (Blog answer2 : blog.getAnswers()) {
                                if (answer2.getId_comentario().equals(id2)) {
                                    new_puntos = answer2.getPuntos() + 1;
                                    answer2.setPuntos(new_puntos);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Puntos agregados con éxito";
    }

    @PostMapping("/puntos/min/{id}")
    public String minPointsLevel0(@PathVariable Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();
        int new_puntos;
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    new_puntos = blog.getPuntos()+1;
                    blog.setPuntos(new_puntos);
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Puntos disminuidos con éxito";
    }

    @PostMapping("/puntos/min/{id}/{id1}")
    public String minPointsLevel1(@PathVariable Integer id, @PathVariable Integer id1) {
        ObjectMapper objectMapper = new ObjectMapper();
        int new_puntos;
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    // Encuentra el comentario de segundo nivel en la lista de answers
                    for (Blog answer : blog.getAnswers()) {
                        if (answer.getId_comentario().equals(id1)) {
                            new_puntos = answer.getPuntos()+1;
                            answer.setPuntos(new_puntos);
                            break;
                        }
                    }
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Puntos disminuidos con éxito";
    }

    @PostMapping("/puntos/min/{id}/{id1}/{id2}")
    public String minPointsLevel2(@PathVariable Integer id, @PathVariable Integer id1, @PathVariable Integer id2) {
        ObjectMapper objectMapper = new ObjectMapper();
        int new_puntos;
        try {
            // Lee el archivo JSON y convierte su contenido a una lista de objetos Blog
            List<Blog> comments = objectMapper.readValue(new File(FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Blog.class));
            for (Blog blog : comments) {
                if (blog.getId_comentario().equals(id)) {
                    // Encuentra el comentario de segundo nivel en la lista de answers
                    for (Blog answer : blog.getAnswers()) {
                        if (answer.getId_comentario().equals(id1)) {
                            // Encuentra el comentario de segundo nivel en la lista de answers
                            for (Blog answer2 : blog.getAnswers()) {
                                if (answer2.getId_comentario().equals(id2)) {
                                    new_puntos = answer2.getPuntos() + 1;
                                    answer2.setPuntos(new_puntos);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }

            // Escribe la lista actualizada en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), comments);

        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        return "Puntos disminuidos con éxito";
    }
}

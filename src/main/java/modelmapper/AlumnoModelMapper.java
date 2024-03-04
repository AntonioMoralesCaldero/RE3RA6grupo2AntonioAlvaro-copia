package modelmapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.model.AlumnoModel;

@Service
public class AlumnoModelMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public AlumnoModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<AlumnoModel> convertirAlumnosAAlumnoModel(Iterable<Alumno> alumnos) {
        return StreamSupport.stream(alumnos.spliterator(), false)
                .map(alumno -> modelMapper.map(alumno, AlumnoModel.class))
                .collect(Collectors.toList());
    }
}

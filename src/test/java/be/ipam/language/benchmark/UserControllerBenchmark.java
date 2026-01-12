package be.ipam.language.benchmark;

import be.ipam.language.api.controller.UserController;
import be.ipam.language.api.dto.RoleEntityDto;
import be.ipam.language.api.dto.UserEntityDto;
import be.ipam.language.api.mapper.UserEntityMapper;
import be.ipam.language.bll.services.Iservices.IUserService;
import be.ipam.language.dao.entities.UserEntity;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;


public class UserControllerBenchmark {

    @State(Scope.Benchmark)
    public static class BenchState {

        UserController controller;

        @MockitoBean
        IUserService userService;

        @MockitoBean
        UserEntityMapper mapper;

        UserEntity userEntity;
        UserEntityDto userDto;

        @Setup(Level.Iteration)
        public void setup() {
            controller = new UserController(userService, mapper);

            userEntity = new UserEntity();
            userEntity.setId(1L);
            Set<RoleEntityDto> roles = new HashSet<>();
            userDto = new UserEntityDto(1L, "John", "Doe", roles);

            when(userService.findAllUsers()).thenReturn(Collections.singletonList(userEntity));
            when(userService.findUserById(1L)).thenReturn(userEntity);
            when(userService.saveUser(any())).thenReturn(userEntity);
            when(mapper.toDto(any())).thenReturn(userDto);
            doNothing().when(userService).deleteUser(1L);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public ResponseEntity<List<UserEntityDto>> benchGetAllUsers(BenchState state) {
        return state.controller.getAllUsers();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public ResponseEntity<UserEntityDto> benchGetUserById(BenchState state) throws Exception {
        return state.controller.getUserById(1L);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public ResponseEntity<UserEntityDto> benchCreateUser(BenchState state) {
        return state.controller.createUser(state.userDto);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public ResponseEntity<UserEntityDto> benchUpdateUser(BenchState state) throws Exception {
        return state.controller.updateUser(1L, state.userDto);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public ResponseEntity<Void> benchDeleteUser(BenchState state) throws Exception {
        return state.controller.deleteUser(1L);
    }
}

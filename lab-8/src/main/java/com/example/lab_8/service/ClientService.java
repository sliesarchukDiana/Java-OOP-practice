package com.example.lab_8.service;

import com.example.lab_8.dto.ClientDto;
import com.example.lab_8.mapper.ClientMapper;
import com.example.lab_8.model.Client;
import com.example.lab_8.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientDto getClientById(Integer id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Transactional
    public ClientDto createClient(ClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Transactional
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
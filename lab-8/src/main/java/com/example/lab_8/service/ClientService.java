package com.example.lab_8.service;

import com.example.lab_8.dto.ClientDto;
import com.example.lab_8.exception.ResourceNotFoundException;
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

    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto getClientById(Integer id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + id + " not found"));
    }

    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Transactional
    public ClientDto updateClient(Integer id, ClientDto dto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

        clientMapper.updateClientFromDto(dto, existingClient);
        return clientMapper.toDto(clientRepository.save(existingClient));
    }
    @Transactional
    public void deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}
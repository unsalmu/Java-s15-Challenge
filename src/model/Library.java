package model;

import repository.InMemoryLibraryRepository;

public class Library {
    private final InMemoryLibraryRepository repository;

    public Library(InMemoryLibraryRepository repository) {
        this.repository = repository;
    }

    public InMemoryLibraryRepository getRepository() {
        return repository;
    }
}
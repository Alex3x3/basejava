package ru.topjava.webapp.storage;


import ru.topjava.webapp.model.Resume;

class MapResumeStorageTest extends AbstractStorageTest {

    MapResumeStorageTest() {
        super(new MapResumeStorage());
    }
}
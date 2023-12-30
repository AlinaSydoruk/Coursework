package com.example.trpzmacrosproject.interpreters.readers;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractFileReader {
  public abstract List<File> getAllScripts() throws IOException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.application.service;

import biz.cogitare.cogitaretraindetails.application.exceptions.FileReaderException;
import java.util.List;

/**
 *
 * @author Sam
 */
public interface FileReaderService {
    /**
     *
     * @param resourceURI The address of a file inside the resources folder.
     * @param header - Header information to be avoided during parsing the file
     * contents.
     * @return - file contents as a list
     * @throws
     * biz.cogitare.cogitaretraindetails.application.exceptions.FileReaderException
     */
    public List<String> readFile(final String resourceURI, final String header) throws FileReaderException;
}

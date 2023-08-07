package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.Host;
import com.cscc43.mybnb.repository.implementation.HostRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping(path = "/")
public class HostController {

  @Autowired
  HostRepository hostRepository;

  @GetMapping("/getAllHosts")
  public List<Host> getAllHosts() {
    return hostRepository.getAllHosts();
  }

  @GetMapping("/getHost")
  public @ResponseBody Host getHost(@RequestParam("id") int id) {
    return hostRepository.getHost(id);
  }

  @PostMapping("/addHost")
  public void addHost(@RequestBody Host host) {
    hostRepository.addHost(host);
  }

  @PostMapping("/deleteHost")
  public void deleteHost(@RequestParam("id") int id) {
    hostRepository.deleteHost(id);
  }
}

package com.meri.murdermysterygame.controller;

import com.meri.murdermysterygame.dto.InterviewDto;
import com.meri.murdermysterygame.exception.ObjectNotFoundException;
import com.meri.murdermysterygame.service.InterviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping("/getAll")
    public List<InterviewDto> getAllInterviews(){
        return interviewService.getAllInterviewDtoList();
    }

    @GetMapping("/get")
    public InterviewDto getInterview(@RequestParam("id") Long id){
        InterviewDto interviewDto = null;
        try {
            interviewDto = interviewService.getInterviewById(id);
        } catch (ObjectNotFoundException e) {
            System.out.println("interview cannot be found");
        }
        return interviewDto;
    }

    @PostMapping("/add")
    public InterviewDto addInterview(@RequestBody InterviewDto interviewDto) throws ObjectNotFoundException {
        return interviewService.createInterview(interviewDto);
    }

    @PutMapping("/update")
    public InterviewDto updateInterview(@RequestBody InterviewDto interviewDto, @RequestParam("id") Long id) throws ObjectNotFoundException {
        return interviewService.updateInterview(interviewDto, id);
    }

    @DeleteMapping("/delete")
    public void deletePInterview(@RequestParam("id") Long id) {
        try {
            InterviewDto interviewDto = interviewService.getInterviewById(id);
            interviewService.deleteInterview(interviewDto);
        } catch (ObjectNotFoundException e) {
            System.out.println("interview cannot be found to delete");
        }
    }
}

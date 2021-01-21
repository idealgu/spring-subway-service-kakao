package subway.path.ui;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import subway.path.application.PathService;
import subway.path.dto.PathResponse;

@Controller
@RequestMapping("/paths")
public class PathController {
    // TODO: 경로조회 기능 구현하기

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }

    @GetMapping
    public ResponseEntity<PathResponse> findPath(@RequestParam("source") Long sourceId, @RequestParam("target") Long targetId) {
        return ResponseEntity.ok().body(pathService.findPath(sourceId, targetId));
    }
}
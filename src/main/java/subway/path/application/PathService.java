package subway.path.application;

import org.springframework.stereotype.Service;
import subway.line.application.LineService;
import subway.line.domain.Lines;
import subway.path.domain.Graph;
import subway.path.domain.Path;
import subway.path.dto.PathResponse;
import subway.station.application.StationService;
import subway.station.domain.Station;

@Service
public class PathService {

    private LineService lineService;
    private StationService stationService;

    public PathService(LineService lineService, StationService stationService) {
        this.lineService = lineService;
        this.stationService = stationService;
    }

    public PathResponse findPath(Long sourceId, Long targetId) {
        Lines lines = new Lines(lineService.findLines());
        Station sourceStation = stationService.findStationById(sourceId);
        Station targetStation = stationService.findStationById(targetId);
        Graph graph = new Graph(lines.getUniqueStations(), lines.getAllSections());
        Path path = new Path(graph.getPathStations(sourceStation, targetStation), graph.getPathDistance(sourceStation, targetStation));
        return PathResponse.of(path);
    }
}
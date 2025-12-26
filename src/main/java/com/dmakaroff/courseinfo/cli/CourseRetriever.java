package com.dmakaroff.courseinfo.cli;
import com.dmakaroff.courseinfo.cli.service.CourseRetrieveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String ...args) {
        LOG.info("CourseRetriever started!");


        if (args.length == 0) {
            LOG.warn("Please add arguments in command line");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch(Exception ex) {
            LOG.error("Unexpected Error");
            ex.printStackTrace();
        }

    }


    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieve Course for '{}': ", authorId);
        CourseRetrieveService courseRetrieveService = new CourseRetrieveService();
        var courses = courseRetrieveService.getCoursesFor(authorId);

        LOG.info("Retrieved the follow courses {}", courses);
    }
}

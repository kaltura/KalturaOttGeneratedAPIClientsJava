package com.kaltura.client.test.utils.ingestUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.kaltura.client.test.tests.BaseTest.*;

public class IngestEpgUtils extends BaseIngestUtils {

    // TODO: think about ENUMS if we really need it here? should we create new ENUM class for it? where?
    public static final String DURATION_PERIOD_DAYS = "days";
    public static final String DURATION_PERIOD_HOURS = "hours";
    public static final String DURATION_PERIOD_MINUTES = "minutes";
    public static final String DURATION_PERIOD_SECONDS = "seconds";

    public static List<String> durationPeriodNames = new ArrayList<>();

    static {
        durationPeriodNames.add(DURATION_PERIOD_DAYS);
        durationPeriodNames.add(DURATION_PERIOD_HOURS);
        durationPeriodNames.add(DURATION_PERIOD_MINUTES);
        durationPeriodNames.add(DURATION_PERIOD_SECONDS);
    }

    // INGEST EPG PARAMS
    public static final int EPG_DEFAULT_COUNT_OF_SEASONS = 1;
    public static final int EPG_DEFAULT_COUNT_OF_PROGRAMMES = 2;
    public static final int EPG_DEFAULT_PROGRAM_DURATION = 30;
    public static final String EPG_DEFAULT_PROGRAM_DURATION_PERIOD_NAME = DURATION_PERIOD_MINUTES;


    public static String buildIngestEpgXml(String epgChannelName, int programsNum, String firstProgramStartDate, int programDuration,
                                           String programDurationPeriodName, boolean isCridUnique4AllPrograms, int seasonsNum,
                                           String coguid, String crid, String seriesId) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestEPG.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user and password
        doc.getElementsByTagName("userName").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // EpgChannels
        Element epgChannels = (Element) doc.getElementsByTagName("EpgChannels").item(0);
        epgChannels.setAttribute("parent-group-id", String.valueOf(partnerId));
        epgChannels.setAttribute("group-id", String.valueOf(partnerId + 1));

        // channel
        Element channel = (Element) epgChannels.getElementsByTagName("channel").item(0);
        channel.setAttribute("id", epgChannelName);

        // programme
        String datePattern = "MM/yy/dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Calendar startDate = Calendar.getInstance();
        try {
            startDate.setTime(dateFormat.parse(firstProgramStartDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        int seasonId = 1;
        while (seasonId < seasonsNum) {
            int programId = 1;
            while (programId < programsNum) {
                Date endDate = loadEndDate(startDate.getTime(), programDuration, programDurationPeriodName);

                Element programmeNode = getProgrammeNode(doc, programId, df.format(startDate.getTime()), df.format(endDate), epgChannelName,
                        coguid, crid, "Program", df.format(Calendar.getInstance().getTime()), seriesId,
                        String.valueOf(seasonsNum), isCridUnique4AllPrograms);
                epgChannels.appendChild(programmeNode);

                startDate.setTime(endDate);
                programId++;
            }
            seasonId++;
        }

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }

    private static Element getProgrammeNode(Document doc, int idx, String startDate, String endDate, String channel, String coguid, String crid, String programNamePrefix,
                                            String currentDate, String seriesId, String seasonNumber, boolean isCridUnique4AllPrograms) {
        String name = programNamePrefix + "_" + startDate + "_ser" + seriesId + "_seas" + seasonNumber + "_e" + idx;
        String cridValue = isCridUnique4AllPrograms ? crid + "_" + seasonNumber + "_" + idx : crid + "_" + idx;

        // programme
        Element programme = doc.createElement("programme");
        programme.setAttribute("start", startDate);
        programme.setAttribute("stop", endDate);
        programme.setAttribute("channel", channel);
        programme.setAttribute("external_id", coguid + "_" + seasonNumber + "_" + idx);

        // title
        Element title = doc.createElement("title");
        title.setAttribute("lang", "eng");
        title.setTextContent(name);
        programme.appendChild(title);

        // crid
        Element cridElement = doc.createElement("crid");
        cridElement.setTextContent(cridValue);
        programme.appendChild(cridElement);

        // desc
        Element desc = doc.createElement("desc");
        desc.setAttribute("lang", "eng");
        desc.setTextContent(startDate + " until " + endDate);
        programme.appendChild(desc);

        // date
        Element date = doc.createElement("date");
        date.setTextContent(currentDate);
        programme.appendChild(date);

        // language
        Element lang = doc.createElement("language");
        lang.setAttribute("lang", "eng");
        lang.setTextContent("eng");
        programme.appendChild(lang);

        // season num meta
        programme.appendChild(generateMetasNode(doc, "season_num", seasonNumber));

        // series id meta
        programme.appendChild(generateMetasNode(doc, "series_id", seriesId));

        // episode num meta
        programme.appendChild(generateMetasNode(doc, "episode_num", String.valueOf(idx)));

        return programme;
    }

    private static Element generateMetasNode(Document doc, String metaTypeString, String metaValuesString) {
        // metas node
        Element metas = doc.createElement("metas");

        // metaType
        Element metaType =  doc.createElement("MetaType");
        metaType.setTextContent(metaTypeString);
        metas.appendChild(metaType);

        // metaValues
        Element metaValues = doc.createElement("MetaValues");
        metaValues.setAttribute("lang", "eng");
        metaValues.setTextContent(metaValuesString);
        metas.appendChild(metaValues);

        return metas;
    }

    private static Element generateTagsNode(Document doc, String tagTypeString, String tagValuesString) {
        // tags node
        Element tags = doc.createElement("tags");

        // TagType
        Element tagType =  doc.createElement("TagType");
        tagType.setTextContent(tagTypeString);
        tags.appendChild(tagType);

        // TagValues
        Element tagValues = doc.createElement("TagValues");
        tagValues.setAttribute("lang", "eng");
        tagValues.setTextContent(tagValuesString);
        tags.appendChild(tagValues);

        return tags;
    }

    static Date loadEndDate(Date startDate, int durationValue, String periodName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        switch (periodName) {
            case DURATION_PERIOD_DAYS:
                calendar.add(Calendar.DATE, durationValue);
                break;
            case DURATION_PERIOD_HOURS:
                calendar.add(Calendar.HOUR, durationValue);
                break;
            case DURATION_PERIOD_MINUTES:
                calendar.add(Calendar.MINUTE, durationValue);
                break;
            case DURATION_PERIOD_SECONDS:
                calendar.add(Calendar.SECOND, durationValue);
        }
        return calendar.getTime();
    }

}

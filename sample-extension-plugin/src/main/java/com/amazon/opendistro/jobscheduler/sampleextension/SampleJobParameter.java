/*
 *   Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file. This file is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *   express or implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 */

package com.amazon.opendistro.jobscheduler.sampleextension;

import com.amazon.opendistro.jobscheduler.spi.ScheduledJobParameter;
import com.amazon.opendistro.jobscheduler.spi.schedule.Schedule;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.time.Instant;

/**
 * A sample job parameter.
 *
 * It adds an additional "indexToWatch" field to {@link ScheduledJobParameter}, which stores the index
 * the job runner will watch.
 */
public class SampleJobParameter implements ScheduledJobParameter {
    // public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String ENABLED_FILED = "enabled";
    public static final String LAST_UPDATE_TIME_FIELD = "last_update_time";
    public static final String SCHEDULE_FIELD = "schedule";
    public static final String ENABLE_TIME_FILED = "enable_time";
    public static final String INDEX_NAME_FIELD = "index_name_to_watch";

    // private String jobId;
    private String jobName;
    private Instant lastUpdateTime;
    private Instant enableTime;
    private boolean isEnabled;
    private Schedule schedule;
    private String indexToWatch;

    public SampleJobParameter() {
    }

    public SampleJobParameter(String id, String name, String indexToWatch, Schedule schedule) {
        // this.jobId = id;
        this.jobName = name;
        this.indexToWatch = indexToWatch;
        this.schedule = schedule;

        Instant now = Instant.now();
        this.isEnabled = true;
        this.enableTime = now;
        this.lastUpdateTime = now;
    }

    /*
    @Override
    public String getJobId() {
        return this.jobId;
    }
    */

    @Override
    public String getName() {
        return this.jobName;
    }

    @Override
    public Instant getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    @Override
    public Instant getEnableTime() {
        return this.enableTime;
    }

    @Override
    public Schedule getSchedue() {
        return this.schedule;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getIndexToWatch() {
        return this.indexToWatch;
    }

    /*
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    */

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setLastUpdateTime(Instant lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setEnableTime(Instant enableTime) {
        this.enableTime = enableTime;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setIndexToWatch(String indexToWatch) {
        this.indexToWatch = indexToWatch;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject();
        builder//.field(ID_FIELD, this.jobId)
                .field(NAME_FIELD, this.jobName)
                .field(ENABLED_FILED, this.isEnabled)
                .field(SCHEDULE_FIELD, this.schedule)
                .field(INDEX_NAME_FIELD, this.indexToWatch);
        if(this.enableTime != null) {
            builder.timeField(ENABLE_TIME_FILED, ENABLE_TIME_FILED, this.enableTime.toEpochMilli());
        }
        if(this.lastUpdateTime != null) {
            builder.timeField(LAST_UPDATE_TIME_FIELD, LAST_UPDATE_TIME_FIELD, this.lastUpdateTime.toEpochMilli());
        }
        builder.endObject();
        return builder;
    }
}

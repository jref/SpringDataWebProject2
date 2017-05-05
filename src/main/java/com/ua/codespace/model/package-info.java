@NamedQueries({
        @NamedQuery(
                name = "findAllUsers",
                query = "select u from User u"
        )
})

@GenericGenerator(name = "ID_GENERATOR_POOLED",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter(
                        name = "sequence_name",
                        value = "POOLED_ID_SEQUENCE"
                ),
                @Parameter(
                        name = "increment_size",
                        value = "10"
                ),
                @Parameter(
                        name = "optimizer",
                        value = "pooled-lo"
                )
        })
package com.ua.codespace.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;

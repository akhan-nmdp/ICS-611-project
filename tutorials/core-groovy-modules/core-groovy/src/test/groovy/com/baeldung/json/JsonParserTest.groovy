package com.baeldung.json

import groovy.json.JsonGenerator
import spock.lang.Specification

import java.text.SimpleDateFormat

class JsonParserTest extends Specification {

    JsonParser jsonParser

    void setup() {
        jsonParser = new JsonParser()
    }

    def 'Should parse to Account given Json String'() {
        given:
        def json = '{"id":"1234","value":15.6}'

        when:
        def account = jsonParser.toObject(json)

        then:
        account
        account instanceof Account
        account.id == '1234'
        account.value == 15.6
    }

    def 'Should format date and exclude value field'() {
        given:
        def account = new Account(
                id: '123',
                value: 15.6,
                createdAt: new SimpleDateFormat('MM/dd/yyyy').parse('14/01/2023')
        )

        def jsonGenerator = new JsonGenerator.Options()
                .dateFormat('MM/dd/yyyy')
                .excludeFieldsByName('value')
                .build()

        when:
        def accountToJson = jsonGenerator.toJson(account)

        then:
        accountToJson == '{"id":"123","createdAt":"01/31/2024"}'
    }

    /*def 'Should parse to Account given Json String with date property' () {
        given:
            def json = '{"id":"1234","value":15.6,"createdAt":"2018-01-01T00:00:00+0000"}'
        when:
            def account = jsonParser.toObjectWithIndexOverlay(json)
        then:
            account
            account instanceof Account
            account.id == '1234'
            account.value == 15.6
            println account.createdAt
            account.createdAt == Date.parse('yyyy-MM-dd', '2018-01-01')
    }*/

    /*def 'Should parse to Json given an Account object' () {
        given:
            Account account = new Account(
                    id: '123',
                    value: 15.6,
                    createdAt: new SimpleDateFormat('MM/dd/yyyy').parse('01/01/2018')
            )
        when:
            def json = jsonParser.toJson(account)
        then:
            json
            json == '{"value":15.6,"createdAt":"2018-01-01T00:00:00+0000","id":"123"}'
    }*/

    def 'Should prettify given a json string'() {
        given:
        String json = '{"value":15.6,"createdAt":"01/01/2018","id":"123456"}'

        when:
        def jsonPretty = jsonParser.prettyfy(json)

        then:
        jsonPretty
        jsonPretty == '''\
{
    "value": 15.6,
    "createdAt": "01/01/2018",
    "id": "123456"
}'''
    }
}

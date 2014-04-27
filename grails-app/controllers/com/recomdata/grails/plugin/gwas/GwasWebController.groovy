/*************************************************************************
 * tranSMART - translational medicine data mart
 *
 * Copyright 2008-2012 Janssen Research & Development, LLC.
 *
 * This product includes software developed at Janssen Research & Development, LLC.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
 * 1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
 * 2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 ******************************************************************/


package com.recomdata.grails.plugin.gwas

class GwasWebController {

    def gwasWebService

    def computeGeneBounds = {
        def snpSource = params.snpSource;
        if (!snpSource) {snpSource = '19'}
        def results = gwasWebService.computeGeneBounds(params.geneSymbol, '0', snpSource)
        renderDataSet(results)
    }

    def getGeneByPosition = {
        def snpSource = params.snpSource;
        if (!snpSource) {snpSource = '19'}
        def results = gwasWebService.getGeneByPosition(params.chromosome, params.long('start'), params.long('stop'), snpSource)
        renderDataSet(results)
    }

    def getModelInfoByDataType = {

        def type = "NONE"
        def typeId = params.long('dataType')

        switch (typeId) {
            case 1: type = "GWAS"; break;
            case 2: type = "EQTL"; break;
            case 3: type = "Metabolic GWAS"; break;
        }

        def results = gwasWebService.getModelInfo(type)
        renderDataSet(results)
    }
	
	def getSecureModelInfoByDataType = {
		
				def type = "NONE"
				def typeId = params.long('dataType')
				def cUser = params.user
		
				switch (typeId) {
					case 1: type = "GWAS"; break;
					case 2: type = "EQTL"; break;
					case 3: type = "Metabolic GWAS"; break;
				}
		
				def results = gwasWebService.getSecureModelInfo(type,cUser)
				renderDataSet(results)
			}
    def resultDataForFilteredByModelIdGeneAndRangeRev = { //TODO Negotiate this name into something more reasonable
        def snpSource = params.snpSource;
        if (!snpSource) {snpSource = '19'}
        def range = params.long('range') ?: 0
        def analysisIds = params.modelId.split(",")
        def sourceId = null
        def geneName = params.geneName

        def geneBounds = gwasWebService.computeGeneBounds(geneName, '0', snpSource)
        def low = geneBounds[0]
        def high = geneBounds[1]
        def chrom = geneBounds[2]
        def results = gwasWebService.getAnalysisDataBetween(analysisIds, low-range, high+range, chrom, snpSource)

        renderDataSet(results)
    }

    def getSnpSources = {
        renderDataSet([[18,"HG18",18,"03-2006","http://www.example.com"], [19,"HG19",19,"02-2009","http://www.example.com"]])
    }

    def getGeneSources = {
        renderDataSet([[0,"GRCh37",0,"01-2001","http://www.example.com"]])
    }

    def getRecombinationRatesForGene = {
        def range = params.long('range') ?: 0
        def geneName = params.geneName

        def results = gwasWebService.getRecombinationRatesForGene(geneName, range)

        renderDataSet(results)
    }

    def snpSearch = {
        def range = params.long('range') ?: 0
        def rsId = params.snp
        def hgVersion = params.snpSource ?: '19'
        def analysisIds = params.modelId.split(",")

        def results = gwasWebService.snpSearch(analysisIds, range, rsId, hgVersion)

        renderDataSet(results)
    }

    def recombinationRateBySnp = {
        def range = params.long('range') ?: 0
        def snp = params.snp
        def hgVersion = params.snpSource ?: '19'
        def results = gwasWebService.getRecombinationRateBySnp(snp, range, hgVersion)

        renderDataSet(results)
    }

    def renderDataSet(results) {

        render(contentType:"text/xml",encoding:"UTF-8") {
            rows() {
                for (result in results) {
                    row() {
                        for (dat in result) {
                            data(dat)
                        }
                    }
                }
            }
        }

    }
}

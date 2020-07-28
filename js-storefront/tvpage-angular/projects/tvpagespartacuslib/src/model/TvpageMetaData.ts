export interface TvpageMetaData {
    html: TvpageMetaHtmlData;
}

export interface TvpageMetaHtmlData {
    head: TvpageMetaHtmlHeadData;
}

export interface TvpageMetaHtmlHeadData {
    tags: MetaTagData[];
}

export interface MetaTagData {
    tag: string;
    value?: string;
    attributes?: MetaTagAttributeData[];
}

export interface MetaTagAttributeData {
    attribute: string;
    value: string;
}
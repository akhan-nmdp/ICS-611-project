// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.baeldung.serialization.protocols;

public final class UserProtos {
    private UserProtos() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public interface UserOrBuilder extends
        // @@protoc_insertion_point(interface_extends:protobuf.User)
        com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        int getId();

        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        java.lang.String getName();

        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        com.google.protobuf.ByteString getNameBytes();
    }

    /**
     * Protobuf type {@code protobuf.User}
     */
    public static final class User extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:protobuf.User)
        UserOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use User.newBuilder() to construct.
        private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private User() {
            name_ = "";
        }

        @java.lang.Override
        @SuppressWarnings({ "unused" })
        protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
            return new User();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private User(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                    case 0:
                        done = true;
                        break;
                    case 8: {

                        id_ = input.readInt32();
                        break;
                    }
                    case 18: {
                        java.lang.String s = input.readStringRequireUtf8();

                        name_ = s;
                        break;
                    }
                    default: {
                        if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return com.baeldung.serialization.protocols.UserProtos.internal_static_protobuf_User_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return com.baeldung.serialization.protocols.UserProtos.internal_static_protobuf_User_fieldAccessorTable.ensureFieldAccessorsInitialized(com.baeldung.serialization.protocols.UserProtos.User.class,
                com.baeldung.serialization.protocols.UserProtos.User.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;

        /**
         * <code>int32 id = 1;</code>
         * @return The id.
         */
        @java.lang.Override
        public int getId() {
            return id_;
        }

        public static final int NAME_FIELD_NUMBER = 2;
        private volatile java.lang.Object name_;

        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            }
        }

        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1)
                return true;
            if (isInitialized == 0)
                return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
            if (id_ != 0) {
                output.writeInt32(1, id_);
            }
            if (!getNameBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1)
                return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream.computeInt32Size(1, id_);
            }
            if (!getNameBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof com.baeldung.serialization.protocols.UserProtos.User)) {
                return super.equals(obj);
            }
            com.baeldung.serialization.protocols.UserProtos.User other = (com.baeldung.serialization.protocols.UserProtos.User) obj;

            if (getId() != other.getId())
                return false;
            if (!getName().equals(other.getName()))
                return false;
            if (!unknownFields.equals(other.unknownFields))
                return false;
            return true;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(com.google.protobuf.ByteString data) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(com.google.protobuf.ByteString data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseDelimitedFrom(java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseDelimitedFrom(java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(com.google.protobuf.CodedInputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static com.baeldung.serialization.protocols.UserProtos.User parseFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(com.baeldung.serialization.protocols.UserProtos.User prototype) {
            return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code protobuf.User}
         */
        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:protobuf.User)
            com.baeldung.serialization.protocols.UserProtos.UserOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
                return com.baeldung.serialization.protocols.UserProtos.internal_static_protobuf_User_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return com.baeldung.serialization.protocols.UserProtos.internal_static_protobuf_User_fieldAccessorTable.ensureFieldAccessorsInitialized(com.baeldung.serialization.protocols.UserProtos.User.class,
                    com.baeldung.serialization.protocols.UserProtos.User.Builder.class);
            }

            // Construct using com.baeldung.serialization.compare.UserProtos.User.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                name_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
                return com.baeldung.serialization.protocols.UserProtos.internal_static_protobuf_User_descriptor;
            }

            @java.lang.Override
            public com.baeldung.serialization.protocols.UserProtos.User getDefaultInstanceForType() {
                return com.baeldung.serialization.protocols.UserProtos.User.getDefaultInstance();
            }

            @java.lang.Override
            public com.baeldung.serialization.protocols.UserProtos.User build() {
                com.baeldung.serialization.protocols.UserProtos.User result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public com.baeldung.serialization.protocols.UserProtos.User buildPartial() {
                com.baeldung.serialization.protocols.UserProtos.User result = new com.baeldung.serialization.protocols.UserProtos.User(this);
                result.id_ = id_;
                result.name_ = name_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }

            @java.lang.Override
            public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
                return super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.baeldung.serialization.protocols.UserProtos.User) {
                    return mergeFrom((com.baeldung.serialization.protocols.UserProtos.User) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.baeldung.serialization.protocols.UserProtos.User other) {
                if (other == com.baeldung.serialization.protocols.UserProtos.User.getDefaultInstance())
                    return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getName()
                    .isEmpty()) {
                    name_ = other.name_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
                com.baeldung.serialization.protocols.UserProtos.User parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (com.baeldung.serialization.protocols.UserProtos.User) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_;

            /**
             * <code>int32 id = 1;</code>
             * @return The id.
             */
            @java.lang.Override
            public int getId() {
                return id_;
            }

            /**
             * <code>int32 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object name_ = "";

            /**
             * <code>string name = 2;</code>
             * @return The name.
             */
            public java.lang.String getName() {
                java.lang.Object ref = name_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    name_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string name = 2;</code>
             * @return The bytes for name.
             */
            public com.google.protobuf.ByteString getNameBytes() {
                java.lang.Object ref = name_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                    name_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string name = 2;</code>
             * @param value The name to set.
             * @return This builder for chaining.
             */
            public Builder setName(java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                name_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string name = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearName() {

                name_ = getDefaultInstance().getName();
                onChanged();
                return this;
            }

            /**
             * <code>string name = 2;</code>
             * @param value The bytes for name to set.
             * @return This builder for chaining.
             */
            public Builder setNameBytes(com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                name_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }

            // @@protoc_insertion_point(builder_scope:protobuf.User)
        }

        // @@protoc_insertion_point(class_scope:protobuf.User)
        private static final com.baeldung.serialization.protocols.UserProtos.User DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new com.baeldung.serialization.protocols.UserProtos.User();
        }

        public static com.baeldung.serialization.protocols.UserProtos.User getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<User> PARSER = new com.google.protobuf.AbstractParser<User>() {
            @java.lang.Override
            public User parsePartialFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
                return new User(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<User> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<User> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public com.baeldung.serialization.protocols.UserProtos.User getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    private static final com.google.protobuf.Descriptors.Descriptor internal_static_protobuf_User_descriptor;
    private static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_User_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = { "\n\nuser.proto\022\010protobuf\" \n\004User\022\n\n\002id\030\001 \001" + "(\005\022\014\n\004name\030\002 \001(\tB0\n\"com.baeldung.seriali" + "zation.compareB\nUserProtosb\006proto3" };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new com.google.protobuf.Descriptors.FileDescriptor[] {});
        internal_static_protobuf_User_descriptor = getDescriptor().getMessageTypes()
            .get(0);
        internal_static_protobuf_User_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_User_descriptor, new java.lang.String[] { "Id", "Name", });
    }
    // @@protoc_insertion_point(outer_class_scope)
}